package Parsers;

public final class LogEntry {
    private final String ip;
    private final String dateTime;
    private final String methodAndPath;
    private final int status;
    private final long dataSize;
    private final String referer;
    private final String userAgent;

    public LogEntry(String ip, String dateTime, String methodAndPath, int status, long dataSize, String referer, String userAgent) {
        this.ip = ip;
        this.dateTime = dateTime;
        this.methodAndPath = methodAndPath;
        this.status = status;
        this.dataSize = dataSize;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    public String getIp() {return ip;}
    public String getDateTime() {return dateTime;}
    public String getMethodAndPath() {return methodAndPath;}
    public int getStatus() {return status;}
    public long getDataSize() {return dataSize;}
    public String getReferer() {return referer;}
    public String getUserAgent() {return userAgent;}

    @Override
    public String toString() {
        return "ip='" + ip + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", methodAndPath='" + methodAndPath + '\'' +
                ", status=" + status +
                ", dataSize=" + dataSize +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent;
    }
}
