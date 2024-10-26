# Görev Yönetim Sistemi

Bu proje, üye olan kullanıcıların diğer kullanıcılara görev atayabilmesini sağlayan 
bir restful-api ile yazılan bir BackEnd projesidir.

### Kullanılan Teknolojiler;
* Spring Boot
* Hibernate
* PostgreSQL

### Projenin Özellikleri;
* Bu projede JWT Token kullanılmıştır. Kullanıcı kayıt olduktan sonra login olur 
ve elimize bir token geçer. Bu token sayesinde kullanıcının kim olduğuna erişebiliriz.
* Token a sahip kullanıcılar, bir başka kullanıcıya görev atayabilme özelliğine sahiptir.
* Kendisine görev atanan kulanıcılar giriş yaptıktan sonra token ile görevleri görüntüleyebilir.