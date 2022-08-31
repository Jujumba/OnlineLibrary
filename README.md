# OnlineLibrary
 Pseudo online-library written using Spring Data JPA, Boot and Thymeleaf

# Get Started
 To get started. Change the keys in application.properties file so they will true for you, by default the PostgreSQL Driver is loaded in pom.xml. Then launch the app and go to http://localhost:8080/people . There you can create a new people by pressing the 'Create a new person' button. After this you can create a book, and choose it's holder in the creation page at the http://localhost:8080/books/new url. 

# Some features
  If you've created so many books, you can enable a pagination by adding a page parametr to the url (http://localhost:8080/books?page=1). Also, you can sort books by the year adding a sort_by_year parametr (http://localhost:8080/books?sort_by_year=true). You can combine them, like this http://localhost:8080/books?page=1&sort-by-year=true
