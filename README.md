# shopa
```
POST /api/auth/signup
{
    "shopname" : "example",
    "mail" : "example@gmail.com",
    "password" : "exmaple"
}

POST /api/auth/signin
{
    "mailAddress" : "example@gmail.com",
    "password" : "example"
}

GET /api/addons/
GET /api/addons/{shopId}
GET /api/shop/{shopId}
POST /api/addons/{shopId}/{addonId}
DELETE /api/shutdown/{shopId}
```
