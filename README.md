# eurodesign
This is a simple Spring boot app created for a real customer

Website functionalities:

- Clients can submit a "Call me form" and leave feedback. The applications are stored in the db and are accessible via Admin page.
- After the application is sent the client sees a message, that the process has been successful.
- An owner of the website gets a notification via email with details of the applications and feedback. The notification is sent asynchronously.
- The feedback needs to be approved by admin or editor and can be published, edited or deleted.
- Role-based authorization is implemented with Spring Security with a custom login form. There are two roles: Admin and Editor.
- Password are encrypted with the BCrypt encoder.
- Admin can edit, delete and publish feedback, publish photos. Editor can edit but doesn't have the authority to delete feedback and call me forms. 
Inaccessible actions buttons aren't seen by editor, but are visible by admin.
- Admin and editor can upload photos to the website. Such photos must be of a certain format, if not an error message appears.
- Photos and feedback are displayed on the administration page to see if they are displayed properly on the website or not.



Technologies used:

-Backend: Spring boot, Hibernate, Tomcat, Spring Security. CRUD operation are tested with Junit and Mockito.
-Frontend: HTML, CSS, Jquery for validation and animation and thymeleaf for integration with Spring.
