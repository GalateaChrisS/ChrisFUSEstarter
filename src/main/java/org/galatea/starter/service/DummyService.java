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

  public String processCommand(String command, String name) {
    switch(command) {
      case "greet-me":
        return greet(name);
      default:
        return "Command not supported.";
    }
  }

  public String greet(String name) {
    return "Hello " + name + "!";
  }
}
