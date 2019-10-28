package org.galatea.starter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Log
@Service
public class DummyService {

  /**
   * Processes the parameters from the http GET request by calling the proper method
   * @param command first parameter of get request specifying the command
   * @param name name of the person specified
   * @return the result of the user's get request
   */
  public String processCommand(String command, String name) {
    if(command.equals("greet-me")) {
      return greet(name);
    } else {
      return "Command not supported.";
    }
  }

  /**
   * Make a greeting to a specified person
   * @param name name of person being greeted
   * @return "Hello <\c>name</\c>!"
   */
  public String greet(String name) {
    return "Hello " + name + "!";
  }
}
