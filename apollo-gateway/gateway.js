import { ApolloGateway, IntrospectAndCompose } from "@apollo/gateway";
import fs from "fs";
import { startApolloServer } from "./server.js";

// use for Dev
/*const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      { name: "movie", url: "http://localhost:8081/graphql" },
      { name: "talent", url: "http://localhost:8082/graphql" },
      // List of federation-capable GraphQL endpoints...
    ],
  }),
});*/

const gateway = new ApolloGateway({
  supergraphSdl: fs.readFileSync("./supergraph.graphql", "utf8").toString(),
});

// const gatewayWithAutoUpdate = new ApolloGateway({
//   async supergraphSdl({ update, healthCheck }) {
//     // create a file watcher
//     const watcher = watch("./supergraph.graphql");
//     // subscribe to file changes
//     watcher.on("change", async () => {
//       // update the supergraph schema
//       try {
//         const updatedSupergraph = await readFile(
//           "./supergraph.graphql",
//           "utf-8"
//         );

//         // optional health check update to ensure our services are responsive
//         await healthCheck(updatedSupergraph);
//         // update the supergraph schema
//         update(updatedSupergraph);
//       } catch (e) {
//         // handle errors that occur during health check or while updating the supergraph schema
//         console.error(e);
//       }
//     });
//     return {
//       supergraphSdl: await readFile("./supergraph.graphql", "utf-8"),
//       // cleanup is called when the gateway is stopped
//       async cleanup() {
//         watcher.close();
//       },
//     };
//   },
// });

startApolloServer({ port: 4000, gateway });
