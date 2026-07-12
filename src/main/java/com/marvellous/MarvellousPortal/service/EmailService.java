package com.marvellous.MarvellousPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private void send(String to, String subject, String html) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            mailSender.send(msg);
        } catch (Exception e) {
            System.err.println("Email failed to " + to + ": " + e.getMessage());
        }
    }

    private String wrap(String title, String color, String body) {
        return """
            <div style="font-family:Segoe UI,Arial,sans-serif;max-width:600px;margin:0 auto">
              <div style="background:linear-gradient(135deg,%s,#3B82F6);padding:24px;border-radius:12px 12px 0 0;text-align:center">
                <h1 style="color:#fff;margin:0;font-size:22px">🎓 EduTrack</h1>
                <p style="color:rgba(255,255,255,.8);margin:4px 0 0;font-size:13px">MarvellousPortal</p>
              </div>
              <div style="background:#fff;padding:28px;border:1px solid #e2e8f0;border-top:none">
                <h2 style="color:#0f172a;margin:0 0 16px">%s</h2>
                %s
              </div>
              <div style="background:#f8fafc;padding:14px;border-radius:0 0 12px 12px;text-align:center;font-size:12px;color:#94a3b8">
                EduTrack · MarvellousPortal · This is an automated message
              </div>
            </div>
            """.formatted(color, title, body);
    }

    public void sendWelcomeEmail(String to, String name, String role) {
        String body = """
            <p style="color:#475569">Hi <strong>%s</strong>,</p>
            <p style="color:#475569">Welcome to <strong>EduTrack</strong>! Your account has been created successfully.</p>
            <div style="background:#eff6ff;padding:16px;border-radius:8px;margin:16px 0">
              <p style="margin:0;color:#1e40af"><strong>Role:</strong> %s</p>
              <p style="margin:4px 0 0;color:#1e40af"><strong>Portal:</strong> http://localhost:5173</p>
            </div>
            <p style="color:#475569">Login anytime to check your attendance, fees, timetable and notices.</p>
            """.formatted(name, role);
        send(to, "Welcome to EduTrack! 🎓", wrap("Welcome to EduTrack!", "#1e40af", body));
    }

    public void sendFeeReminder(String to, String studentName,
                                String batchName, int amountDue, String dueDate) {
        String body = """
            <p style="color:#475569">Dear <strong>%s</strong>,</p>
            <p style="color:#475569">This is a friendly reminder that your fee payment is pending.</p>
            <div style="background:#fef3c7;padding:16px;border-radius:8px;margin:16px 0;border-left:4px solid #f59e0b">
              <p style="margin:0;color:#92400e"><strong>Batch:</strong> %s</p>
              <p style="margin:6px 0 0;color:#92400e"><strong>Amount Due:</strong> ₹%s</p>
              <p style="margin:6px 0 0;color:#92400e"><strong>Due Date:</strong> %s</p>
            </div>
            <p style="color:#475569">Please contact your admin to complete the payment and avoid any disruption.</p>
            """.formatted(studentName, batchName,
                String.valueOf(amountDue).replaceAll("(\\d)(?=(\\d{3})+$)", "$1,"),
                dueDate);
        send(to, "⚠️ Fee Payment Reminder — EduTrack", wrap("Fee Payment Reminder", "#d97706", body));
    }

    public void sendAttendanceAlert(String to, String studentName,
                                    String batchName, int percentage) {
        String body = """
            <p style="color:#475569">Dear Parent/Guardian of <strong>%s</strong>,</p>
            <p style="color:#475569">We want to inform you that the attendance for your child has dropped below the required level.</p>
            <div style="background:#fee2e2;padding:16px;border-radius:8px;margin:16px 0;border-left:4px solid #dc2626">
              <p style="margin:0;color:#991b1b"><strong>Student:</strong> %s</p>
              <p style="margin:6px 0 0;color:#991b1b"><strong>Batch:</strong> %s</p>
              <p style="margin:6px 0 0;color:#991b1b"><strong>Current Attendance:</strong> %d%%</p>
              <p style="margin:6px 0 0;color:#991b1b"><strong>Required:</strong> 75%%</p>
            </div>
            <p style="color:#475569">Please encourage regular attendance to avoid academic penalties.</p>
            """.formatted(studentName, studentName, batchName, percentage);
        send(to, "🚨 Low Attendance Alert — EduTrack", wrap("Attendance Alert", "#dc2626", body));
    }

    public void sendNoticeEmail(String to, String recipientName,
                                String noticeTitle, String noticeContent) {
        String body = """
            <p style="color:#475569">Dear <strong>%s</strong>,</p>
            <p style="color:#475569">A new notice has been posted on EduTrack.</p>
            <div style="background:#eff6ff;padding:16px;border-radius:8px;margin:16px 0;border-left:4px solid #1e40af">
              <h3 style="margin:0;color:#1e40af">%s</h3>
              <p style="margin:10px 0 0;color:#334155;white-space:pre-wrap">%s</p>
            </div>
            <p style="color:#475569">Login to EduTrack to view more details.</p>
            """.formatted(recipientName, noticeTitle, noticeContent);
        send(to, "📢 New Notice: " + noticeTitle + " — EduTrack",
                wrap("New Notice Posted", "#1e40af", body));
    }
}