## REST API for Ecommerce Shopping Cart
Customer can add and remove from cart, and also get their cart data.

### APIs
---


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

#### ER Diagram:

![Untitled Diagram drawio (1)](https://user-images.githubusercontent.com/91946820/202634792-1262e0a2-4220-48d3-8439-2d9d5ea39478.png)
