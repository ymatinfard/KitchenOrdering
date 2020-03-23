# Kitchen Ordering App(Android)

## Overview

This is a sample Android mobile application project that:
* provides a list of products
* enables product multi-selection and ordering


Application is built on top of MVVM model with the following key layers:

### Repository pattern
A key part is Repository - an interface responsible for providing data
```kotlin
interface Repository {
    suspend fun getUserToken(userAuthData: UserAuthData): UserToken?
    suspend fun getProducts(userToken: String): List<Product>?
    suspend fun saveOrderItems(orderItemsEntity: List<OrderItemsEntity>)
    suspend fun saveOrder(orderEntity: OrderEntity)
    suspend fun getAllOrderItems(orderId: Int): List<OrderItemsEntity>
    suspend fun getOrderItemsTotalPrice(orderId: Int): Int?
    suspend fun RemoveDatabaseInfo()
    fun getAllOrders(): LiveData<List<OrderEntity>>
}
```
Repository implementation is based on both remote and local services.

### Remote service with Coroutine and Retrofit 2

Remote service is responsible for polling to Remote API. It's implementation is based on Retrofit 2 usage.

```kotlin
interface KitchenApi {
    @POST(Constants.LOGIN_URL)
     suspend fun getUserToken(@Body body: UserAuthData): Response<UserToken>

    @GET(Constants.PRODUCT_URL)
    suspend fun getProducts(@Query("token") token: String): Response<List<Product>>
}
```
### Local service with Coroutine and Room

Local service is responsible for storing and retrieving user orders in the local database. 

```kotlin
@Dao
interface OrderDao {

    @Query("SELECT * from order_items_table WHERE order_id = :orderId")
    fun getAllOrderItems(orderId: Int): List<OrderItemsEntity>

    @Query("SELECT * from order_table ORDER BY date_time DESC")
    fun getAllOrders(): LiveData<List<OrderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertOrderItems(orderItems: List<OrderItemsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT SUM(price) FROM order_items_table WHERE order_id = :orderId")
    suspend fun getTotalPrice(orderId: Int): Int

    @Query("DELETE FROM order_items_table")
    suspend fun deleteAllOrderItems()

    @Query("DELETE FROM order_table")
    suspend fun deleteAllOrders()
}
```

## [View] Apply UI changes with RecyclerView 

To obtain the best possible user experience while dealing with product list, I used Recycler View.

```kotlin
 ```
 
 To have fluent navigation single activity approach used.
 
## Test coverage
My intention here was to perform login process test with an appropriate tools/frameworks:
* Unit tests 
* Instrumentation tests with Android Core Testing

### Unit tests
To test Repository class I used Fake DataSource class decoupled from the actual data sources.
```kotlin
class FakeRemoteDataSource(val userToken: UserToken) : DataSource {
    override suspend fun fetchUserToken(userAuthData: UserAuthData): UserToken? {
            return userToken
    }
 ```
 Testing login failure 
 ```kotlin
     @Before
    fun setup_viewmodel() {
        context = ApplicationProvider.getApplicationContext()
        fakeRepository = FakeKitchenRepository()
        loginViewModel = LoginViewModel(context, fakeRepository)
    }
    
     @Test
    fun validate_user_livedata_invalid_username() = runBlocking {
        loginViewModel.validateUser(inValidUserName, passwordTest)
        loginViewModel.isUserLoginSuccessful.observeForever({})
        val loginResult = loginViewModel.isUserLoginSuccessful.value
        assertThat(loginResult, IsEqual(Constants.LOGIN_FAILED))
    }
  ```
For demonstrating purposes, I haven't delivered all tests.

### Instrumentation tests

 ```kotlin
 
     @Test
    fun loginFragment_login_sucecss(){
        val validUsername = "user@gmail.com"
        val validPassword = "password"

        val mockNavController = mock(NavController::class.java)
        launchFragmentInContainer<LoginFragment>(themeResId = R.style.Theme_AppCompat) {
            LoginFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
                    }
                }
            }
        }
        onView(withId(R.id.et_username)).perform(replaceText(validUsername))
        onView(withId(R.id.et_password)).perform(replaceText(validPassword))
        onView(withId(R.id.btn_login)).perform(click())
        verify(mockNavController).navigate(R.id.action_fragment_login_to_dashboardFragment)
    }
 ```

 

