# example
Light4j + GraphQL example
<pre>
inspired by tutorial https://www.howtographql.com/graphql-java/0-introduction/

requirements: 
	jdk-1.8 or later
	maven
	mongoDB
	
Get started:
	1. open terminal
	2. clone the project: git clone https://github.com/Vi-Soft/example.git
	3. cd to example directory: cd Vi-Soft-NG/java-example/example/
	4. to compile and run: mvn clean package exec:exec
	5. open browser with URL: http://localhost:8080/graphql

Build data:
run createUsers in GraphiQL:

mutation createUsers{
  firstUser:createUser(name: "John Doe", authProvider:{email: "john@example.com" passwd:"secret"}){
    id
    name
  }
  secondUser:createUser(name: "Jane Doe", authProvider:{email:"jane@example.com" passwd: "secret" }){
    id
    name
  }
  thirdUser:createUser(name: "Satoshi Nakamoto", authProvider:{email: "satoshi@example.com" passwd: "secret"}){
    id
    name
  }
}

get one of users id, open file resources/graphiql.html
change id in           
             headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer 5b056d57ef022328b08876c9'
            }
with your id after "Bearer "

restart the system

Build data:

run createLinks

mutation createLinks {
  firstLink: createLink(url: "https://www.howtographql.com/graphql-java/0-introduction/", description: "GraphQL tutorial") {
    url
    description
  }
  secondLink: createLink(url: "https://coinmarketcap.com/", description: "cryptocurrencies info") {
    url
    description
  }
  thirdLink: createLink(url: "https://bitcoin.org/bitcoin.pdf", description: "Bitcoin: A Peer-to-Peer Electronic Cash System. Satoshi Nakamoto") {
    url
    description
  }
  forthLink: createLink(url: "http://graphql-java.readthedocs.io/en/latest/schema.html", description: "DataFetcher and TypeResolver") {
    url
    description
  }
  fifthLink: createLink(url: "https://www.google.com", description: "the google search engine") {
    url
    description
  }
}

run createVotes with your linkId, userId

mutation createVotes {
  firstVote: createVote(linkId: "5b057372ef02234b9092f1a1", userId: "5b056d57ef022328b08876c7") {
    createdAt
    user {
      name
    }
    link {
      url
      description
    }
  }
  secondVote: createVote(linkId: "5b057372ef02234b9092f1a1", userId: "5b056d57ef022328b08876c8") {
    createdAt
    user {
      name
    }
    link {
      url
      description
    }
  }
  thirdVote: createVote(linkId: "5b057372ef02234b9092f1a1", userId: "5b056d57ef022328b08876c9") {
    createdAt
    user {
      name
    }
    link {
      url
      description
    }
  }
}
</pre>
