# stw-chat: Chat based on JSF

This is a class assignment for the Web Systems and Technologies course of Universitat Autònoma de Barcelona's Computer Engineering Degree. 
The goal of this exercise was to get familiar with JSF and websockets implementing a very basic support service chat application.

There are two kind of users: visitors and support staff. The former are able to log in choosing a nickname and product details, and the 
latter shoulld log-in using a login/password pair. Visitor-staff assignment is carried out so that the visitor is 
assigned to the least busy staff member. Messages are passed back and forth to the intended destinations. UI is updated so that
it shows when some user who's not the current active conversation has written a new message.
