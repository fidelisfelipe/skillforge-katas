package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EventHandler - Record Deconstruction Patterns")
class EventHandlerTest {

    private EventHandler handler;

    public record Point(int x, int y) {}
    public record Rectangle(Point topLeft, Point bottomRight) {}

    @BeforeEach
    void setUp() {
        handler = new EventHandler();
    }

    @Test
    @DisplayName("Deve descrever Point usando record deconstruction")
    void should_describe_point_using_deconstruction() {
        String result = handler.describe(new Point(3, 4));
        assertNotNull(result);
        assertTrue(result.contains("3"), "Resultado deve conter coordenada x=3: " + result);
        assertTrue(result.contains("4"), "Resultado deve conter coordenada y=4: " + result);
    }

    @Test
    @DisplayName("Deve descrever Rectangle usando deconstrução aninhada")
    void should_describe_rectangle_using_nested_deconstruction() {
        Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 20));
        String result = handler.describe(rect);
        assertNotNull(result);
        assertTrue(result.toLowerCase().contains("rectangle") || result.toLowerCase().contains("rect"),
                "Resultado deve mencionar Rectangle: " + result);
        assertTrue(result.contains("10") && result.contains("20"),
                "Resultado deve conter coordenadas do canto: " + result);
    }

    @Test
    @DisplayName("Deve calcular área de retângulo a partir dos componentes deconstruídos")
    void should_compute_area_from_deconstructed_components() {
        Rectangle rect = new Rectangle(new Point(0, 0), new Point(5, 4));
        int area = handler.area(rect);
        assertEquals(20, area);
    }

    @Test
    @DisplayName("Deve calcular área zero para retângulo degenerado")
    void should_compute_zero_area_for_degenerate_rectangle() {
        Rectangle rect = new Rectangle(new Point(2, 3), new Point(2, 3));
        int area = handler.area(rect);
        assertEquals(0, area);
    }

    @Test
    @DisplayName("Deve retornar descrição padrão para objeto desconhecido")
    void should_return_default_for_unknown_object() {
        String result = handler.describe("not a record");
        assertNotNull(result);
        assertTrue(result.toLowerCase().contains("unknown") || result.toLowerCase().contains("desconhecido"),
                "Resultado deve indicar tipo desconhecido: " + result);
    }
}
