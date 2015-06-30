<h1>TriviDao</h1>

<h2>Introduction</h2>

The TriviDao project is a small fun yocto-framework (yocto as in 10<sup>−24</sup>, 2 interfaces and 2 abstract classes) that I use for small Spring-Hibernate projects.

For me this is useful, as I am no longer bothered by thinking about the PKs for the POJOs nor by basic CRUD operations. So I am lazy, but that is a good thing :)

For now, keep in mind that I am old, so I am using older libraries versions, like in the case of both Spring and Hibernate I am in the 3.x bronze age, not like the new kids on the block with their shiny 4.x versions.

<h2>Usage</h2>

The normal use case is that all POJOs extend AbstractPersistentBean and all DAOs extend HibernateGenericDao and you get basic functionality out of the box.

I know, having all public methods for operations like findAll() might be dangerous, as people might use it on large data sets, which might not be the best idea, so use responsibly.

Of course this is subject to any constructive criticism, so have at it!

<h2>Samples</h2>

I also introduced a small sample for a proof of concept. It's not much, but it's a starting point. Have a look at org.tupol.sample_1.SampleApp inside the src/samples/java directory.

The sample is using an HSQLDB in-memory database, so no fancy DB setup required to run the sample.