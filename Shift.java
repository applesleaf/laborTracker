package AmznLaborTracking;

import java.time.Duration;
import java.time.LocalDateTime;

public class Shift {
	
	private String shiftId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int calculateHoursWorked() {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        return (int) hours;
    }

}
