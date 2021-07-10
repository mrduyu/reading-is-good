# Reading Is Good
This is a case named reading is good which was sent to me to develop.
## Technologies
  - #### Spring Boot
  - #### MongoDB
  - #### Docker

Here our endpoints below before we run our project. We will send requests these endpoints.
We can run the project on our local device as well as on **Docker**.

| Service  | Endpoint |  Purpose |
| ------------- | ------------- | ------------- |
| Customer Service | http://localhost:8102/authenticate | It is used for login.
| Customer Service| http://localhost:8102/register | It is used for registration.
| Book Service | http://localhost:8102/books/list | It is used for listing all books.
| Book Service | http://localhost:8102/books/insert | It is used for inserting a new book.
| Book Service | http://localhost:8102/book/update | It is used for updating book stock.
| Book Service | http://localhost:8102/book/update | It is used for updating book stock.
| Order Service | http://localhost:8102/orders| It is used for listing orders of customer.
| Order Service | http://localhost:8102/orders | It is used for ordering a book or removing a book from customer basket.
| Order Service | http://localhost:8102/orders/{orderId} | It is used for fetching order detail by order id.
| Order Service | http://localhost:8102/orders/{startDate}/{endDate}| It is used for listing orders between given dates.
| Order Service | http://localhost:8102/books/{bookId}| It is used for fetching book detail by book id.
| Statistics Service | http://localhost:8102/orderstatistics| It is used for fetching order statistics of customer.

After we clone/download project into our local device, we can make it run on Docker by using these steps.
## How can we run our project on Docker?
- Open terminal and go to your local folder where project has been downloaded
- Write docker-compose up --build -d and hit Enter.
    ```sh
    $ docker-compose up --build -d
    ```
- You will wait a little to finish process.
- When everything is done you should see an image below.
- ![github.small](https://user-images.githubusercontent.com/40163745/125173682-1613bb00-e1c9-11eb-9246-39ca41bde074.PNG)
- When you want to see container list. Write **docker ps** and hit Enter.
    ```sh
    $ docker ps
    ```
 - ![github.small](https://user-images.githubusercontent.com/40163745/125173742-70148080-e1c9-11eb-9287-0114d76984aa.PNG)  
 - When you want to see image list. Write **docker images** and hit Enter.
 - ![github.small](https://user-images.githubusercontent.com/40163745/125173763-95a18a00-e1c9-11eb-98be-e7c78d974908.PNG)
 - If you want to stop all containers and remove all containers and images. Write **docker-compose down --rmi all** and hit Enter.
 - ![image](https://user-images.githubusercontent.com/40163745/125173819-def1d980-e1c9-11eb-98a8-1c8dd78ac27f.PNG)


### If your containers are standing and everything is completed, you can test rest endpoint via Postman.
### Sample Postman Requests
These screenshots are only some examples. If you would like to try it yourself, you can find **Postman Collection** folder in repo.

![image](https://user-images.githubusercontent.com/40163745/125173846-11033b80-e1ca-11eb-8a76-c26a7f029d62.PNG)
![image](https://user-images.githubusercontent.com/40163745/125173894-71927880-e1ca-11eb-9670-8f23875d41a8.PNG)
![image](https://user-images.githubusercontent.com/40163745/125173952-ca621100-e1ca-11eb-86f6-d88b06a2bc3a.PNG)
![image](https://user-images.githubusercontent.com/40163745/125173951-c930e400-e1ca-11eb-9abb-aff901b1db65.PNG)

  Best Regards,
  **Emre**





