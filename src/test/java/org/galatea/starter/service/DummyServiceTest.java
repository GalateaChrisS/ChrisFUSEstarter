package org.galatea.starter.service;

import org.galatea.starter.ASpringTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;


public class DummyServiceTest extends ASpringTest {


  @Test
  public void testProcessCommandGreetMeWithName() {
    String command = "greet-me";
    String name = "Chris";
    String expectedResult = "Hello Chris!";

    DummyService service = new DummyService();
    DummyService spyService = spy(service);

    doReturn("Hello Chris!").when(spyService).greet(name);

    String result = spyService.processCommand(command, name);

    assertEquals(expectedResult, result);
  }

  @Test
  public void testGreet() {
    String name = "Chris";
    String expectedResult = "Hello Chris!";

    DummyService service = new DummyService();

    String result = service.greet(name);
    assertEquals(expectedResult, result);
  }

  @Test
  public void testUnsupportedInput() {
    String command = "invalid command";
    String expectedResult = "Command not supported.";

    DummyService service = new DummyService();

    String result = service.processCommand(command, "");
    assertEquals(expectedResult, result);
  }

}

