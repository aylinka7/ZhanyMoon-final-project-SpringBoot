# Backend – Aalam Kids Educational Center

This repository contains the backend part of the web application developed for the educational center **Aalam Kids**. The backend is responsible for handling course data, processing applications submitted by users, and providing secure administrative functionality for managing incoming requests.

The backend is designed to work together with the frontend website, serving data through a RESTful API and ensuring that sensitive administrative operations are protected.

## Server Startup

The server is hosted on a platform that uses a sleep mode for inactive services.  
When accessing the backend after a period of inactivity, it may take around **3 minutes** for the server to wake up and start responding to requests. This behavior is expected and does not indicate an error.

## Public API Endpoints

The following endpoints are publicly accessible and do not require authentication:

- **GET `/api/courses`**  
  Returns a list of all available courses. This endpoint is used by the frontend to display course information to website visitors.

- **POST `/api/applications`**  
  Allows users to submit an application for a course. This endpoint is used when parents leave a request through the website.

## Admin-Only API Endpoints

The following endpoints are available **only to authenticated administrators**. Proper authorization is required to access these routes, and they are not publicly accessible.

- **GET `/api/applications`**  
  Returns a list of all submitted applications. This endpoint is used in the admin panel to review incoming requests.

- **DELETE `/api/applications`**  
  Allows administrators to delete applications that have already been processed or are no longer needed.

## Authentication and Authorization

The backend includes an authentication and authorization system to protect sensitive data and administrative functionality. Only authorized administrators can access admin-only endpoints.

All administrative actions require authentication, and unauthorized requests are denied. This ensures that application data and personal information are not publicly accessible.

## Database Deployment

The database for this project was independently deployed as part of the backend infrastructure.  
Initially, MySQL was considered, but due to deployment limitations on the hosting platform, it was not possible to deploy MySQL successfully.

As a result, the project uses **PostgreSQL**, which was deployed on **Render** and fully integrated with the backend. PostgreSQL provides reliable data storage and works seamlessly with the deployed server environment.

## Telegram Bot Notifications

To improve workflow efficiency, the backend includes integration with a **Telegram bot**.  
Every time a new application is submitted, the bot automatically sends a notification to administrators.

This allows admins to instantly receive information about new applications directly on their mobile devices, without the need to constantly check the admin panel. As a result, response time is reduced and communication becomes more efficient.

## Project Purpose

This backend was developed for a real educational center and is intended to handle real user data. The system focuses on reliability, security, and seamless integration with the frontend.

The backend architecture allows future improvements, such as extended admin roles, application status tracking, or additional notification channels.
