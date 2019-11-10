# Notes Application

## About
The Notes Application is build using Spring Boot framework, and MySQL database is used as a storage. 
We have the _notesapp_ database with _notes_ and _users_ tables. 
There are 3 users hardcoded into the _users_ table

1. email: user1@email.com, password: user1password
2. email: user2@email.com, password: user2password
3. email: user3@email.com, password: user3password

Passwords are stored in the db with SHA1 encrypted versions.

## Install and run

Please make sure that `docker` and `docker-compose` are installed before running the app. 

`notes-0.0.1.jar` is already pushed as part of the code.

Run docker containers:
```shell script
cd /path/to/the/repo
mkdir ./data # stores the mysql db
docker-compose up
```
Docker compose will spin up an instance of MySQL db and an instance of Spring Boot application. 

## Usage
```shell script
# POST a new note
curl \
  -X POST \
  -d '{"title": "new title","note": "new note"}' \
  -H "Content-Type: application/json" \
  --user user1@email.com:user1password \
  http://0.0.0.0:8080/notes
# creates a new note which gets its user_id from the authenticated user and its created_at and updated_at are the current timestamp. 
# In the above example a new note will be created with specified title and note text, with user_id 1.
# Other fields could also be provided (userId, createdAt or updatedAt) but the API won't allow to manually update them,
# instead it will ignore the fields and set the proper values.


# GET note by its Id
curl \
  --user user1@email.com:user1password \
  http://0.0.0.0:8080/notes/1
# if the note was not created by the currently authenticated user it should not retrieve the note
# and respond with 403 Forbidden

# PUT update existing note
curl \
  -X PUT \
  -d '{"title": "another title","note": "another note"}' \
  -H "Content-Type: application/json" \
  --user user1@email.com:user1password \
  http://0.0.0.0:8080/notes/2
# if the note is not found it should 404. If the note does not belong to the current user it should 403.
# otherwise it will update the title and/or note text with specified values

# DELETE note by id
curl \
  -X DELETE \
  --user user1@email.com:user1password \
  http://0.0.0.0:8080/notes/1
# if the note is not found it should 404. If the note does not belong to the current user it should 403.
# otherwise it will delete the note with the specified Id
```

## Additional Notes

Please note that the following repo is not ready for production (hence the jar uploaded as part of the code).
In order to make this production ready number of additional tasks need to be performed (such as build pipelines, comprehensive testing, etc) that are out of the scope of this exercise.