package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActorHierarchyTest {
    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testStartAndStopActors() {
        //#start-stop-main
        ActorRef first = system.actorOf(StartStopActor1.props(), "first");
        first.tell("stop", ActorRef.noSender());
        //#start-stop-main
    }

    @Test
    public void testSuperviseActors() {
        //#supervise-main
        ActorRef supervisingActor = system.actorOf(SupervisingActor.props(), "supervising-actor");
        supervisingActor.tell("failChild", ActorRef.noSender());
        //#supervise-main
    }
}
