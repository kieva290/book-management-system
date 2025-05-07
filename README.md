# book-management-system docker compose Spring Boot MySQL

## Overview

This application is built using Spring Boot and MySQL, serving a solid foundation for server application development project

## Setup project

its crucial to first build the target JAR file "book-management-system-0.0.1-SNAPSHOT.jar", which is gets stored inside the target folder 

from the root folder for the project this command "mvn clean build", will build the application and create JAR file first

### Dockerized Environment

The entire application has been dockerized for easy deployment and management, to get started , ensure you have Docker Desktop installed on your machine,

follow this link to install Docker Desktop [official Docker documentation](https://docs.docker.com/engine/install/)

### Launching the application

To launch the application, navigate to the root directory of the project and run the following command

```bash
docker-compose up --build -d
```

This command builds and starts the application within a Docker container, the application will be accessible on port 8080

To bring own the application and associated containers, use the following command:

```bash
docker-compose down --rmi all
```
The `--rmi all` flag removes all associated images, if you wish to retain the images for future use, simply omit this flag from the command

### Testing

This application comes with unit tests, for the Services classes

### Endpoints

- **Create Book**: `/api/v1/books`
    - Method: `POST`
    - Description: Create new book end-point.
    - Response:
        - HTTP Status Code:
            - `200 OK` - ID of the generated book.
    - Sample Request:
      ```json
      {
        "title": "Theory of Algorithms",
        "author": "Kev"
      }
      ```
#### Get All Books

- **Method**: `GET`
- **URL**: `/api/v1/books`
- **Description**: Retrieves all Books.
- **Response**: Returns paginated list of all Books.

#### Update the Book, for the supplied book-id

- **Update Book**: `/api/v1/books/{book-id}`
    - Method: `PUT`
    - Description: Update book end-point.
    - Response:
        - HTTP Status Code:
            - `200 OK` - Response object of updated Book details.
    - Sample Request:
      ```json
      {
        "id": 3,
        "title": "Theory of Algorithms",
        "author": "Kev"
      }
      ```

#### Get Book for the supplied book-id

- **Method**: `GET`
- **URL**: `/api/v1/books/{book-id}`
- **Description**: Retrieves Book, for the supplied book-id.
- **Response**: Returns Response Object, if book exists.

- **Get Book**: `/api/v1/books/{book-id}`
    - Method: `GET`
    - Description: Update book end-point.
    - Response:
        - HTTP Status Code:
            - `200 OK` - Response object of updated Book details.
    - Sample Request:
      ```json
      {
        "id": 3,
        "title": "Theory of Algorithms",
        "author": "Kev"
      }
      ```

#### Delete Book, for the supplied book-id, if it exists

- **Method**: `DELETE`
- **URL**: `/api/v1/books/{book-id}`
- **Description**: Deletes Book, for the supplied book-id.
- **Response**: Returns 204 Response, for successful delete.

- **Delete Book**: `/api/v1/books/{book-id}`
    - Method: `DELETE`
    - Description: Delete book end-point.
    - Response:
        - HTTP Status Code:
            - `204 No Content` - Nothing is returned.


#### Search for Books for the supplied search-phrase

- **Method**: `GET`
- **URL**: `/api/v1/books/search/{search-phrase}`
- **Description**: Retrieves Books, for the supplied search-phrase.
- **Response**: Returns List of Books, if books exists.

- **Retrieve Books**: `/api/v1/books/search/{search-phrase}`
    - Method: `GET`
    - Description: Update book end-point.
    - Response:
        - HTTP Status Code:
            - `200 OK` - Response List of Books.

### Notes

-  Replace `book-id` with the actual ID of the Book when making requests to update or delete a specific book
-  To test the endpoint via POSTMAN, l've supplied the export file for POST in the source code, file "book-management-system.postman_collection.json", just import it on your local machine POSTMAN,
-  You will notice the endpoint like this "{{api_url}}{{api_port}}/api/v1/books", just ensure that these variables "api_url : http://localhost:", and "api_port: 8080" are setup on POSTMAN
-  before making requests, or you can reach out to me for assistance 