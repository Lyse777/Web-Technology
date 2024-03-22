WEB TECHNOLOGY AND INTERNET MIDTERM PROJECT
=================================================
 1. IDE: I Used Eclipse
 2. Database: I Uesd PostgreSQL.
 3. Browser: Chrome and Edge.
 4. Languages: Java (Servlet), HTML, CSS, Javascript and Jsp.
 ---------------------------------------------------------------
1. Implementation  CRUD using doGet, doPost, doDelete.

2. Add another table of users which has three columns (id as primary key, email, password, and 
role). This is where the users will save his/her information when he is creating an account.

3. Refer to AUCA website and implement the same design with all pages as specified on its 
navigation bar. Link: https://auca.ac.rw/

5. Functional requirements:
   ===============================================================
1. The user should be able to create an account with email, password, and admin as role.
2. The user should be able to create an account with email, password, and student as role.
3. The user should be able to create an account with email, password, and teacher as role.
4. The user should be redirected to home at first login.
5. The system should implement authentication and authorization based his role:
6.  If the user has the role of admin, he should be able to visit all pages of the application.
7.  If the user has the role of student, he should be able to visit all sub menus under academic.
8.  If the user has the role of student, he should be able to visit the application page.
9.  If the user has the role of teacher, he should be able to visit the research and media center sub menus.
10.  When the user logs out, he should be authenticated if he tries to bypass the login page.
11. User cookie or session to hold information in user’s credentials in browser.
---------------------------------------------------------------------------------------
Implement Exception handling page for 404.
Implement exception handling page for 500.
---------------------------------------------------------------------------------------
When the user last 1 minutes without interacting with the system. The system should redirect him/her to login page. Here, you will use session
---------------------------------------------------------------------------------------------
