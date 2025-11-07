# **Kiefer's Ink CMS**

## _A modular and simple Website and CMS for Kiefer's Ink Tattoo and Piercing Studio._

The project includes the implementation of a public website and an admin panel application. The public-facing website will showcase tattoo and piercing services, artist profiles, image galleries, and provide an inquiry form for potential customers. The admin panel application will allow administrators to manage the shop’s website content, including services, artists, portfolios, and inquiries.

---

> <p align="center">
>  <em>This project was developed as a partial requirement for</em><br>
>  <strong>ITS181-2 — Application Development 2
> </p>

---

### Tech Stack

- **Website**: _Angular_
- **Admin Client**: _Angular_
- **Backend**: _Spring Boot_
- **Database**: _MySQL_

---

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
