# eurodesign
This is a website for a real customer. The website in production is on another server and domain. This is a copy located on my vps for demonstration purposes.

Website functionalities:

- Clients can submit a "Call me form" and leave feedback. The applications are stored in the db and are accessible via Admin page.
- After the application is sent the client sees a message, that the process has been successful.
- An owner of the website gets a notification via email with details of the applications and feedback. The notification is sent asynchronously.
- The feedback needs to be approved by admin or editor and can be published, edited or deleted.
- Role-based authorization is implemented with Spring Security with a custom login form. There are two roles: Admin and Editor.
- Password are encrypted with the BCrypt encoder.
- Admin can edit, delete and publish feedback, publish photos. Editor can edit but doesn't have the authority to delete feedback and call me forms. 
Inaccessible action buttons can't be seen by editor, but are visible by admin.
- Admin and editor can upload photos to the website. Such photos must be of a certain format, if not an error message appears.
- Photos and feedback are displayed on the administration page to see if they are displayed properly on the website or not.



Technologies used:

-Backend: Spring boot, Hibernate, Tomcat, Spring Security. CRUD operation are tested with Junit and Mockito.
-Frontend: HTML, CSS, Jquery for validation and animation and thymeleaf for integration with Spring.


You can check my copy of this website here is a link:
http://eurodesign009.tk/

Admin Page:
http://eurodesign009.tk/admin/

Roles:
Admin. Can perfom all CRUD operations
Username: admin
Password: admin


Editor. Cannot perfom "delete" operations.
Username: editor
Password: editor
