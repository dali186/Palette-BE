package fc.server.palette._common.exception.message;

import lombok.Getter;

public class LogMessage {
    @Getter
    private final String methodName;
    private final String className;
    private final int lineNumber;

    private LogMessage(StackTraceElement element) {
        this.methodName = element.getMethodName();
        this.className = element.getClassName();
        this.lineNumber = element.getLineNumber();
    }

    public static LogMessage of(StackTraceElement element) {
        return new LogMessage(element);
    }

    public String getClassNameAndLine() {
        return this.className + ", " + lineNumber;
    }
}