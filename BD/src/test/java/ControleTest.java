import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import control.Controle;
import model.ReadWrite;

public class ControleTest {

	@Test
	public void checkpointTest() throws IOException {
		Controle.checkpoint();
		List<String> list = ReadWrite.readFile("log_disco");
		assertFalse("Lista esta vazia", list.isEmpty());
	}

}
