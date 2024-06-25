package validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.ansaf.pojo.SetCommandObject;
import org.ansaf.validator.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {
  private InputValidator inputValidator;

  @BeforeEach
  void setup(){
    inputValidator = new InputValidator();
  }

  @Test
  void testValidGetInput(){
    boolean actual = inputValidator.validateInput("GET STRINGKEY");
    assertTrue(actual);
  }

  @Test
  void testValidSetStringInput(){
    boolean actual = inputValidator.validateInput("SET STRING STRING STRINGKEY");
    assertTrue(actual);
  }

  @Test
  void testValidSetIntInput(){
    boolean actual = inputValidator.validateInput("SET INT key 1234");
    assertTrue(actual);
  }

  @Test
  void testValidSetNegativeIntInput(){
    boolean actual = inputValidator.validateInput("SET INT key -1234");
    assertTrue(actual);
  }

  @Test
  void testValidSetInvalidIntInput(){
    boolean actual = inputValidator.validateInput("SET INT key -1qew234");
    assertFalse(actual);
  }

  @Test
  void testInvalidSetInput(){
    boolean actual = inputValidator.validateInput("SET STRING ");
    assertFalse(actual);
  }

  @Test
  void testInvalidGetInput(){
    boolean actual = inputValidator.validateInput("GET");
    assertFalse(actual);
  }

  @Test
  void testInvaliSGetInput(){
    boolean actual = inputValidator.validateInput("SET");
    assertFalse(actual);
  }

  @Test
  void testInvalidCommands(){
    boolean actual = inputValidator.validateInput("PUT STRING STRINGKEY");
    assertFalse(actual);
  }

  @Test
  void testNotValidExtraInfo(){
    boolean actual = inputValidator.validateInput("GET STRING STRINGKEY");
    assertFalse(actual);
  }

  @Test
  void testNotValidEmpty(){
    boolean actual = inputValidator.validateInput("");
    assertFalse(actual);
    actual = inputValidator.validateInput(" ");
    assertFalse(actual);
    actual = inputValidator.validateInput("\t");
    assertFalse(actual);
    actual = inputValidator.validateInput("\n");
    assertFalse(actual);
    actual = inputValidator.validateInput(null);
    assertFalse(actual);
  }

  @Test
  void testGetSetCommand(){
    SetCommandObject setCommandObject = inputValidator.getSetCommand("SET STRING KEY VALUE");
    assertEquals("STRING", setCommandObject.getType());
    assertEquals("KEY", setCommandObject.getKey());
    assertEquals("VALUE", setCommandObject.getData());
  }

}
