# **Kiefer's Ink CMS**

## _A modular and simple Website and CMS for Kiefer's Ink Tattoo and Piercing Studio._

The project includes the implementation of a public website and an admin panel application. The public-facing website will showcase tattoo and piercing services, artist profiles, image galleries, and provide an inquiry form for potential customers. The admin panel application will allow administrators to manage the shop’s website content, including services, artists, portfolios, and inquiries.

---

> <p align="center">
>  <em>This project was developed as a partial requirement for</em><br>
>  <strong>ITS181-2 — Application Development 2</strong>
> </p>

---

## Tech Stack

- **Website**: _Angular_
- **Admin Client**: _Angular_
- **Backend**: _Spring Boot_
- **Database**: _MySQL_

### Project Structure

```
kiefersink/               --> parent folder (plain)
├── kiefersink-backend    --> backend parent project (Maven)
│   ├── kieferbiz         --> business logic module (models)
│   ├── kieferdata        --> data access module (entities, repositories)
│   ├── sbkieferms        --> backend module (Spring Boot)
├── kiefersink-website    --> website client (Angular)
├── kiefersink-admin      --> admin dashboard client (Angular)
```

## Getting Started

> ### _Prerequisites_
>
> - **Java 17+** _(for Spring Boot backend)_
> - **Node.js 22+ and npm** _(for Angular clients)_
> - **MySQL 8+** _(for the database)_
> - **Maven** _(for building the backend modules)_

Follow these steps to set up and run Kiefer's Ink CMS locally.

1. **Database Setup**
   - Update the database configuration in `application.yml` in `sbkieferms/src/main/resources` with your username, password, and database name.
   - Optionally, run the SQL scripts provided to initialize tables.
2. **Run the Backend**

   ```powershell
    # Navigate to the Spring Boot module
    cd kiefersink/kiefersink-backend

    # Build the project using Maven
    mvn clean install

    # Navigate to sbkieferms and Run the backend server
    cd kiefersink/kiefersink-backend/sbkieferms
    mvn spring-boot:run
   ```

3. **Run the Website Client**

   ```powershell
    # Navigate to the website client
    cd kiefersink/kiefersink-website

    # Install dependencies
    npm install

    # Start the Angular development server
    ng serve
   ```

4. **Run the Admin Client**

   ```powershell
    # Navigate to the admin client
    cd kiefersink/kiefersink-admin

    # Install dependencies
    npm install

    # Start the Angular development server
    ng serve
   ```
