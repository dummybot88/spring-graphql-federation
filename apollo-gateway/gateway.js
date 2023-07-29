const { ApolloServer } = require("@apollo/server");
const { startStandaloneServer } = require("@apollo/server/standalone");
const { ApolloGateway, IntrospectAndCompose } = require("@apollo/gateway");

// use for Dev
const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      { name: "movie", url: "http://localhost:8081/graphql" },
      { name: "talent", url: "http://localhost:8082/graphql" },
      // List of federation-capable GraphQL endpoints...
    ],
  }),
});

async function startApolloServer() {
  const server = new ApolloServer({
    debug: true,
    gateway,
    subscriptions: false,
  });

  const { url } = await startStandaloneServer(server, {
    listen: { port: 4000 },
  });
  console.log(`🚀  Server ready at ${url}`);
}

startApolloServer();
