# SpringDataAndJPQL

### EntityManager API

The `EntinyManager` interface provides an api to perform queries in **JPQL** syntax on the database:

`TypedQuery <Person> query = em.createQuery ("select person from Person person", Persona.class);`

**Note that JPQL uses the name of the classes / entities**, not the name of the DB tables.
The query returns a set or a single result. Notice what is on the left of the operator `=` in the expression (it's typed !!):
~~~
List <Person> resultat = query.getResultList ();
Unique person = query.getSingleResult ();
~~~
If we want to use parameters in the JPQL query, we must:

- Build the query

`TypedQuery <Person> query = em.createQuery ("select person from Person person where nom =: nom", Persona.class);`
- Indicate the name and value of these parameters

`query.setParameter ("nom", "Joan");`
- Run the query

`List <Person> resultat = query.getResultList ();`

The query to the BBDD can return so many results that compromises the **performance** of our app, since things in real life are not as the 
legend says "*640K software is all the memory anybody would ever need on a computer*."

To determine which will be the first result (starting with 0) or what will be the **maximum results to be returned**:
~~~
query.setFirstResult (int);
query.setMaxResults (int);
~~~

### JPQL - Java Persistence Query Language
We have a query language for our JPA application very similar to **SQL**:

**Note that JPQL uses the name of the classes / entities, not the name of the tables in the database**, and the entities / classes are 
navigated through their properties (`Java`):
~~~
select person
from Person person
where persona.nom = 'Joana'
~~~
#### THE CONSULTATIONS RETURN THE TYPE OF OBJECT THAT YOU HAVE REQUESTED
~~~
Select person
from Person person
~~~
returns objects of the `Person` type.
~~~
select persona.nom
from Person person
~~~
Returns objects of type `String`, if `nom` is of type `String`.

If we use an **aggregation function** like `count`:
~~~
select count (person)
from Person person
~~~
returns a single result of type `Number`.

We can build **arbitrary structures**:
~~~
select persona.nom, persona.municipi.nom
from Person person
~~~
that returns an array of objects `[String, String]`.

#### RELAXATION NAVIGATION
~~~
select person
from Person person
join persona.municipi municipi
where municipi.nom = 'Eivissa'
~~~
Making the relationship between the tables involved in the query **explicit** makes their performance more **efficient**.

#### THETA STYLE JOIN
The **joins** can be written in two styles: **ANSI - style** or **THETA - style**, which is such that:
~~~
select persona.nom
from Person,
Municipi municipi
where municipi.nom = persona.nom
~~~
The other day we talked in class about the **types of joins**. When you make a join between different tables, ***the type of join that you 
use determines the rows that will appear in the result set***.

Here is a review of SQL and PL / SQL:

https://gtusqlplsql.wordpress.com/tag/theta-style/

#### Types of Joins in Oracle

Join is a method used to combine two or more tables, views or materialized views based on a common condition. Sometimes it is 
necessary to work with multiple tables as though they were a single entity. Then a single SQL statement can manipulate data from all 
the tables. Joins are used to achieve this. Tables are joined on columns that have the same data type in the tables.

#### WHERE
Not everything that allows writing SQL in the WHERE is available in JPQL. Take a look at the video to see the most common and 
expanded reference as you solve the exercises.
~~~
Logical operators: not, and, or
Comparisons: =, <,>, <=,> =, <>
Navigation by objects with '.'
Literals: e.g. 'aina'
Subselects
Functions: upper (), length (), substring () ...
~~~
#### Spring Data
It is a product to reduce the volume of code necessary to work with BBDD from Spring.

#### REPOSITORIES

The following code creates a fully functional repository that allows you to perform various types of queries on a table that stores 
people (eye, the person entity you have to **map** to the DB).
~~~
public interface PersonaRepository extends Repository <Person, Long> {
public List <Person> findByNom (String nom);
}
~~~
Notice that:

- It is not a class, it is an **interface**.
- You do not need the `@Repository` annotation to detect it as a component (*bean*).
- **Extend** `Repository`. The `Repository` interface is **parameterized** with the **type of the Entity and the ID of the entity/class**. 
This is an example of the **parametric polymorphism** that we saw when studying the first subject of the java book dedicated to the 
fundamentals of the POO.

#### DERIVED QUERIES
Queries are constructed from the signatures of the methods.

The methods refer to the name of the properties of the class / entity and it is possible to use logical operators and functions:
~~~
public List <Person> findByMunicipiNom (String nom);
public List <Person> findByIdOrNom (long id, String nom);
public List <Person> findByMunicipiNomOrderByNomDesc (String nom);
~~~
#### @QUERY
If the query is very complicated, it may be convenient to write it using JPQL (within our `Repository`).

The previous query:

`public List <Person> findByMunicipiNomOrderByNomDesc (String nom);`

can be written by annotating it with `@Query`.
~~~
@Query ("select per from Person per where per.municipi.nom =? 1 order by per.nom")
public List <Person> obtePersonesPerMunicipi (String municipi);
~~~
or using the parameters step by name:
~~~
@Query ("select per from Person per where per.municipi.nom =: municipi order by per.nom")
public List <Person> obtePersonesPerMunicipi (@Param ("municipi") String municipi);
~~~
In this case, the `@Param` annotation is used to indicate the name of the parameter in the query.

### CRUD Repository
The `CrudRepository<>` interface adds the methods that correspond to the CRUD operations. The interface parameterizes the methods to 
work with the type of the entity (Person) and the type of its Id (Long).
~~~
public interface PersonaRepository extends CrudRepository <Person, Long> {
public List <Person> findByNom (String nom);
}
~~~
- save (entity)
- saveAll (iterable)
- findById (id)
- count ()
- delete (entity)

Remember that each method works for the **type of entity (T) and that of its subtypes (S)**:

`<S extends T> S save (S entity);`

motive by which it is important to rewrite the `equals()` method of the entity so that it is able to compare objects of the same 
type and their subtypes.
