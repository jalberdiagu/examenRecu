package base;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ControlCompuertasTest {

    @Test
    public void testSolicitarPermiso() {
        boolean resultado1 = ControlCompuertas.solicitarPermiso(80);
        assertEquals(true, resultado1);

        boolean resultado2 = ControlCompuertas.solicitarPermiso(30);
        assertEquals(false, resultado2);
    }
}

