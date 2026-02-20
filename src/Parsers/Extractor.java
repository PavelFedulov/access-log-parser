package Parsers;

public class Extractor {
    public static LogEntry extract(String line) {
        int p = 0;

        //IP
        int ipEnd = line.indexOf(' ', p);
        if (ipEnd < 0) return null;
        String ip = line.substring(p, ipEnd);

        p = ipEnd + 1;

        //date
        int dateStart = line.indexOf('[', p);
        int dateEnd;

        if (dateStart < 0) {
            dateEnd = -1;
        } else {
            dateEnd = line.indexOf(']', dateStart);
        }

        if (dateEnd < 0) return null;
        String dateTime = line.substring(dateStart + 1, dateEnd);

        p = dateEnd + 1;

        //method and path
        int methodStart = line.indexOf('"', p);
        int methodEnd;

        if (methodStart < 0) {
            methodEnd = -1;
        } else {
            methodEnd = line.indexOf('"', methodStart + 1);
        }

        if (methodEnd < 0) return null;
        String methodAndPath = line.substring(methodStart + 1, methodEnd);

        p = methodEnd + 1;

        //status
        int statusStart = line.indexOf(' ', p);
        int statusEnd;

        if (statusStart < 0) {
            statusEnd = -1;
        } else {
            statusEnd = line.indexOf(' ', statusStart + 1);
        }

        if (statusEnd < 0) return null;
        String statusStr = line.substring(statusStart + 1, statusEnd);
        int status = Integer.parseInt(statusStr);
        p = statusEnd + 1;


        //data size
        int dataSizeStart = p;
        int dataSizeEnd = line.indexOf(' ', dataSizeStart);

        if (dataSizeEnd < 0) return null;
        String dataSizeStr = line.substring(dataSizeStart, dataSizeEnd);
        long dataSize = Long.parseLong(dataSizeStr);
        p = dataSizeEnd + 1;

        //referer
        int refererStart = p;
        int refererEnd = line.indexOf('"', refererStart + 1);

        if (refererEnd < 0) return null;
        String referer = line.substring(refererStart + 1, refererEnd);
        p = refererEnd + 1;
        System.out.println(line.charAt(p));

        //User-Agent
        int agentStart = line.indexOf('"', p);
        int agentEnd = line.indexOf('"', agentStart + 1);

        if (agentEnd < 0) return null;
        String userAgent = line.substring(agentStart + 1, agentEnd);

        return new LogEntry(ip, dateTime, methodAndPath, status, dataSize, referer, userAgent);
    }
}
