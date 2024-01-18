### How to auto install maven dependencies
```
1.  When opening the project, select the pom.xml and it will auto download the necessary maven dependencies
```

###  Whether we want to use a factory design pattern or not... etc
```
```

### If unable to connect to spring boot server, 401 unauthorized error with postman
```
This is because of the web security dependencies that you install. Either configure it to allow the request or disable it by removing the dependency.
```
### Id of object is null even though id is set to auto increment
```
```
### If your spring boot fails to start
```
This assumes that you already have your application properties information set up to allow the connection to the database.
If you do not have the database set up, use @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) to exclude the auto configuration of the database.
```

### Understanding relationship of @ManyToOne or @OneToMany in spring, doing this will make it bidirectional
```
1. It is important to understand the one who decides the relationship
2. It is also important to understand the cascade type
3. It is also important to understand the mappedBy and the joinColumn
4. Direction of the relationship, unidirectional vs bidirectional

@CascadeType - if all, all changes applies to all tables that the value is reference, e.g. if you delete the specific user, the insurance policies of this user will also be deleted

Avoid using the default .toString for @ManyToOne or @OneToMany as it will cause an infinite loop

TLDR:
@MappedBy - lets spring know that this is the child of the relationship and the foreign key is in the other table
Whichever side possess the @JoinColumn holds the FK column which means that the other side is the child of the relationship.
- @MappedBy should be in the child class
- @JoinColumn should be in the parent class
- @JoinColumn(name = <column name for the foreign key>)

@ManyToOne or @OneToMany usually comes with fetch = FetchType.LAZY, cascade = CascadeType.ALL

Refer to this for more details:
https://dev.to/jhonifaber/hibernate-onetoone-onetomany-manytoone-and-manytomany-8ba 

If this is too complicated, you can avoid the @manyToOne relationships and join the tables yourself, it will also avoid the circular dependency issue
but modifications will be more complex with more tables as you will have to update each tables yourself.
```


### Setting up cors:




### Additional Annotations
```
@Transactional - This is used to ensure that the transaction is completed before committing to the database
@JsonIgnore - This is used to ignore the field when returning the object
@JsonIgnoreProperties - This is used to ignore the field when returning the object
@Rollback - This is used to rollback the transaction
```

### Several questions to answer:
1. How to auto install maven dependencies? For intellij just direct hit into pom.xml
2. Auto creation of tables by spring
3. Id issue with tables, does it auto increment? and auto add an id if not provided?
4. Adding and updating of additional fields automatically like created_at and updated_at and version
5. How to use spring security
6. @jsonbody, @requestParam, @pathvariable for incoming information etc 


