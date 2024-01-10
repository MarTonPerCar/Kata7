package swing;

import java.util.Map;

public record Histogram(String Title, String XAxis, String YAxis, Map<String, Integer> data) {}
