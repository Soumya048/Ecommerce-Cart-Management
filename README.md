## REST API for Ecommerce Cart
Customer can add and remove from cart, and also get their cart data.

### APIs



#### Admin Controller

```
POST 
/admin​/add-product 
addProductsHandler
```
#### User Controller

```
POST
​/user​/register
registerUserHandler
```

```
PATCH
​/user​/add-product-to-cart​/{userId}​/{productId}​/{quantity}
addProductToCartHandler
```

```
PATCH
​/user​/remove-product-from-cart​/{userId}​/{productId}
removeProductFromCartHandler
```

```
GET
​/user​/cart-data​/{userId}
getUserCartDateHandler
```