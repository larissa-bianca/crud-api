# CRUD API 

## Endpoints

### 1. Clients

#### 1.1. List all items

**Path:**

```
GET api/menu/items/
```

**Request Schema:**

No body needed.

**Response Schema:**

| **Name** | **Description** | **Type** |
| :------- | :-------------- | :------: |
| item     | Item's data     |  Object  |

#### 1.2. Get a single item using an id parameter

**Path:**

```
GET api/menu/items/{id}
```

**Request Schema:**

No body needed.

**Response Schema:**

| **Name** | **Description**     |   **Type**    |
| :------- | :------------------ | :-----------: |
| item     | List of item's data | Array[Object] |

#### 1.3. Create an item

**Path:**

```
POST api/menu/items/
```

**Request Schema:**

| **Name**    | **Description**    | **Type** |
| :---------- | :----------------- | :------: |
| name        | Item's name        |  String  |
| price       | Item's price       |   Long   |
| description | Item's description |  String  |
| image       | Item's URL image   |  String  |

**Response Schema:**

| **Name** | **Description** | **Type** |
| :------- | :-------------- | :------: |
| item     | Item's data     |  Object  |

#### 1.4. Update an item

**Path:**

```
PUT api/menu/items/
```

**Request Schema:**

| **Name**    | **Description**    | **Type** |
| :---------- | :----------------- | :------: |
| name        | Item's name        |  String  |
| price       | Item's price       |   Long   |
| description | Item's description |  String  |
| image       | Item's URL image   |  String  |

**Response Schema:**

| **Name** | **Description** | **Type** |
| :------- | :-------------- | :------: |
| item     | Item's data     |  Object  |

#### 1.5. Delete an item using an id parameter

**Path:**

```
DELETE api/menu/items/{id}
```

**Request Schema:**

No body needed.

**Response Schema:**

No body returned.

