package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SalesReporter: agrupamento e coletores de Stream")
class SalesReporterTest {

    private SalesReporter reporter;

    record Sale(String category, String seller, BigDecimal amount) {}

    private List<Sale> sales;

    @BeforeEach
    void setUp() {
        reporter = new SalesReporter();
        sales = List.of(
                new Sale("Books", "Ana", new BigDecimal("100.00")),
                new Sale("Books", "Bruno", new BigDecimal("50.00")),
                new Sale("Toys", "Ana", new BigDecimal("200.00")),
                new Sale("Toys", "Bruno", new BigDecimal("75.00")),
                new Sale("Books", "Ana", new BigDecimal("25.00"))
        );
    }

    @Test
    @DisplayName("deve somar o total de vendas por categoria")
    void should_sum_total_amount_by_category() {
        Map<String, BigDecimal> totals = reporter.totalByCategory(sales);
        assertEquals(0, new BigDecimal("175.00").compareTo(totals.get("Books")));
        assertEquals(0, new BigDecimal("275.00").compareTo(totals.get("Toys")));
        assertEquals(2, totals.size());
    }

    @Test
    @DisplayName("deve contar quantas vendas existem por vendedor")
    void should_count_sales_by_seller() {
        Map<String, Long> counts = reporter.countBySeller(sales);
        assertEquals(3L, counts.get("Ana"));
        assertEquals(2L, counts.get("Bruno"));
    }

    @Test
    @DisplayName("deve agrupar vendas por categoria em listas")
    void should_group_sales_by_category() {
        Map<String, List<Sale>> grouped = reporter.groupByCategory(sales);
        assertEquals(3, grouped.get("Books").size());
        assertEquals(2, grouped.get("Toys").size());
    }

    @Test
    @DisplayName("deve particionar vendas entre alto e baixo valor com base em um limite")
    void should_partition_sales_by_threshold() {
        Map<Boolean, List<Sale>> partition = reporter.partitionByThreshold(sales, new BigDecimal("80.00"));
        assertEquals(2, partition.get(true).size());
        assertEquals(3, partition.get(false).size());
    }

    @Test
    @DisplayName("deve retornar mapa vazio quando não há vendas")
    void should_return_empty_map_when_no_sales() {
        Map<String, BigDecimal> totals = reporter.totalByCategory(List.of());
        assertTrue(totals.isEmpty());
    }
}
