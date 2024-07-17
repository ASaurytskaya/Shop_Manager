# Shop Manager 

## Description

Shop Manager is a comprehensive backend service for managing products and their associated images. This service includes functionalities for creating, updating, retrieving, and deleting products, as well as handling image processing and background removal using the Remove.bg API.

## Installation

1.  Clone the repository:

            git clone https://github.com/ASaurytskaya/Shop_Manager

4. Navigate to the project directory:

            cd shop-manager-api

5. Set up environment variables:
      
   Set your API Key for Remove.bg API.

            export IMAGE_API_KEY=your_api_key

6. Build the project:

            ./gradlew clean build

7. Run the application:

            ./gradlew bootRun

## Usage

Required and Optional Fields

When creating or updating a product, the following fields are available:

#### Required Fields

These fields must be provided for the product to be created or updated successfully:

1. name (String) – The name of the product. 
2. brand (String) – The brand associated with the product.
3. category (String) – The category to which the product belongs.
4. color (String) – The color of the product.
5. price (double) – The price of the product. Must be a positive value. 
6. weight (String) – The weight of the product.
7. availableUnits (int) – The number of units available for sale. 
8. features (list) – Key features of the product.

#### Optional Fields

These fields can be included but are not mandatory:

1. model (String) – The model number or identifier for the product. 
2. rating (Double) – The average rating of the product. Can be used to reflect user reviews.
3. warranty (String) – Warranty information for the product.

NOTE: A list of images associated with the product can be added later.


### Endpoints:

- POST /api/products: Creates a new product.

  Example of JSON request for creating a product:

      {
        "name": "Прекрасные наушники",
        "brand": "Acme",
        "model": null,
        "category": "Электроника",
        "color": "черный",
        "price": 49.99,
        "weight": "200 г",
        "rating": 4.5,
        "warranty": "2 года",
        "available_units": 10,
        "special_features": [
         "Активное шумоподавление",
            "Складной дизайн",
            "Встроенный микрофон"
        ]
      }


- PUT /api/products/{id}: Updates an existing product.

  Example of JSON request for updating a product:

      {
        "name": "Прекрасные наушники",
        "brand": "Acme",
        "model": "A95",
        "category": "Электроника",
        "color": "черный",
        "price": 49.99,
        "weight": "200 г",
        "rating": 4.5,
        "warranty": "2 года",
        "available_units": 10,
        "special_features": [
            "Активное шумоподавление",
            "Складной дизайн",
            "Встроенный микрофон"
        ]
      }


- PUT /api/products/{id}/update_images: Updates an existing product.
  
  Example of JSON request for updating images of a specific product:


      [
        {
            "image_name": "image1.jpg",
            "image_data": null,
            "path_to_image": "src/main/resources/static.images/acme_w_bg_2.jpeg"
        }
      ]

- GET /api/products/{id}: Retrieves a product by its ID.

  Example of JSON response:

      {
        "id": 1,
        "name": "Прекрасные наушники",
        "brand": "Acme",
        "model": "A95 b",
        "category": "Электроника",
        "description": "Прекрасные наушники Acme A95, цвет: черный. Особенности: Активное шумоподавление, Складной дизайн, Встроенный микрофон.",
        "color": "черный",
        "price": 49.99,
        "weight": "200 г",
        "rating": 4.5,
        "warranty": "2 года",
        "images": [
              {
                    "image_name": "image1.jpg",
                    "image_data": "base64/...."
              }
        ],
        "available_units": 10,
        "special_features": [
            "Активное шумоподавление",
            "Складной дизайн",
            "Встроенный микрофон"
        ]
      }

- GET /api/products/highest-rated: Retrieves the highest-rated product.
- GET /api/products/most-expensive: Retrieves the most expensive product.
- GET /api/products/cheapest: Retrieves the cheapest product.
- GET /api/products: Retrieves a list of all products.
- DELETE /api/products/{id}: Deletes a product.
  
  Example of JSON request for deleting a product:

      {
        "id": 1,
        "name": "Прекрасные наушники",
        "brand": "Acme",
        "model": "A95 b",
        "category": "Электроника",
        "description": "Прекрасные наушники Acme A95, цвет: черный. Особенности: Активное шумоподавление, Складной дизайн, Встроенный микрофон.",
        "color": "черный",
        "price": 49.99,
        "weight": "200 г",
        "rating": 4.5,
        "warranty": "2 года",
        "images": [
              {
                    "image_name": "image1.jpg",
                    "image_data": "base64/...."
              }
        ],
        "available_units": 10,
        "special_features": [
            "Активное шумоподавление",
            "Складной дизайн",
            "Встроенный микрофон"
        ]
      }

### Error Handling

The API provides structured error responses for various exceptions. Below are the main exceptions and their corresponding HTTP status codes:

1.	ProductNotFoundException
* Status Code: 404 NOT FOUND
* Description: Thrown when a product with the specified ID is not found.
2. BadRequestException
* Status Code: 400 BAD REQUEST
* Description: Thrown for invalid requests, such as missing required fields.
3.	CannotReadImageFileException
* Status Code: 400 BAD REQUEST
* Description: Thrown when there is an issue reading an image file.
4.	ImageFileNotFoundException
* Status Code: 404 NOT FOUND
* Description: Thrown when the specified image file cannot be found.
5.	Global Exception
* Status Code: 500 INTERNAL SERVER ERROR
* Description: Catches any unhandled exceptions and returns a generic error message.


Example Error Response

    {
      "error_type": "error",
      "message": "No Product found with id 123"
    }
