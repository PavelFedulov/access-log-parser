package Parsers;

public final class UserAgentExtractor {
    public static String extractUserAgent(String userAgent) {
        if (userAgent == null || userAgent.equals("-")) return null;

        int start = userAgent.indexOf("(compatible");
        if (start < 0) return null;

        int end = userAgent.indexOf(')', start + 1);
        if (end < 0) return null;

        String brackets = userAgent.substring(start + 1, end);

        String[] parts = brackets.split(";");
        if (parts.length < 2) return null;

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String fragment = parts[1];
        if (fragment.isEmpty()) return null;

        int slash = fragment.indexOf("/");

        String program;

        if (slash >= 0) {
            program = fragment.substring(0, slash);
        } else {
            program = fragment;
        }

        program = program.trim();

        if (program.isEmpty()) {
            return null;
        } else {
            return program;
        }
    }
}
