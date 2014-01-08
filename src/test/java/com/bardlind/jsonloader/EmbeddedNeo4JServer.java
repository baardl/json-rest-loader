package com.bardlind.jsonloader;

/**
 * Based on code from https://github.com/neo4j/neo4j/blob/2.0.0/community/embedded-examples/src/main/java/org/neo4j/examples/EmbeddedNeo4j.java
 *
 */

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;

import java.io.File;
import java.io.IOException;


public class EmbeddedNeo4JServer {
    private static final String DB_PATH = "target/jsonloader-db";
    GraphDatabaseService graphDb;


    void createDb(String uri) {
        System.out.println("Creating database with uri: " + uri);
        clearDb();
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        registerShutdownHook( graphDb );
    }


    private void clearDb() {
        try {
            FileUtils.deleteRecursively( new File( DB_PATH ) );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
    }

    void removeData()   {
        try ( Transaction tx = graphDb.beginTx() ) {
            // let's remove the data
            //firstNode.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
            //firstNode.delete();
            //secondNode.delete();

            tx.success();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void shutDown()
    {
        System.out.println();
        System.out.println( "Shutting down database ..." );
        graphDb.shutdown();
    }

    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }

}
