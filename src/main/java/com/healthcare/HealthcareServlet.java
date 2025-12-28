package com.healthcare;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Healthcare Management System - Main Servlet
 * 
 * PURPOSE: Handles HTTP requests and generates the healthcare dashboard
 * 
 * WHY SERVLET: Servlets are Java classes that handle HTTP requests in a web container
 * 
 * WHAT IT DOES:
 * 1. Receives HTTP GET requests
 * 2. Generates HTML response with healthcare dashboard
 * 3. Displays system features and status
 */
public class HealthcareServlet extends HttpServlet {
    
    /**
     * Handles GET requests (when user visits the page)
     * 
     * @param request  - Contains request information (URL, parameters, headers)
     * @param response - Used to send response back to client
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set response content type to HTML
        // WHY: Tells browser to render as HTML
        response.setContentType("text/html;charset=UTF-8");
        
        // Get writer to send HTML to browser
        PrintWriter out = response.getWriter();
        
        try {
            // Generate HTML response
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <title>Healthcare Management System</title>");
            out.println("    <style>");
            out.println("        * { margin: 0; padding: 0; box-sizing: border-box; }");
            out.println("        body {");
            out.println("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
            out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
            out.println("            color: white;");
            out.println("            min-height: 100vh;");
            out.println("            padding: 20px;");
            out.println("        }");
            out.println("        .container {");
            out.println("            max-width: 1200px;");
            out.println("            margin: 0 auto;");
            out.println("            text-align: center;");
            out.println("        }");
            out.println("        h1 {");
            out.println("            font-size: 3em;");
            out.println("            margin: 40px 0 20px;");
            out.println("            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);");
            out.println("        }");
            out.println("        .subtitle {");
            out.println("            font-size: 1.5em;");
            out.println("            margin-bottom: 40px;");
            out.println("            opacity: 0.9;");
            out.println("        }");
            out.println("        .success-badge {");
            out.println("            background: #4ade80;");
            out.println("            color: white;");
            out.println("            padding: 15px 30px;");
            out.println("            border-radius: 50px;");
            out.println("            display: inline-block;");
            out.println("            font-size: 1.3em;");
            out.println("            margin: 20px 0;");
            out.println("            box-shadow: 0 4px 15px rgba(0,0,0,0.2);");
            out.println("        }");
            out.println("        .features {");
            out.println("            display: grid;");
            out.println("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));");
            out.println("            gap: 20px;");
            out.println("            margin: 40px 0;");
            out.println("        }");
            out.println("        .feature-card {");
            out.println("            background: rgba(255,255,255,0.1);");
            out.println("            padding: 30px;");
            out.println("            border-radius: 15px;");
            out.println("            backdrop-filter: blur(10px);");
            out.println("            border: 1px solid rgba(255,255,255,0.2);");
            out.println("            transition: transform 0.3s;");
            out.println("        }");
            out.println("        .feature-card:hover {");
            out.println("            transform: translateY(-5px);");
            out.println("        }");
            out.println("        .feature-icon { font-size: 3em; margin-bottom: 15px; }");
            out.println("        .feature-title { font-size: 1.3em; margin-bottom: 10px; font-weight: bold; }");
            out.println("        .feature-desc { opacity: 0.9; line-height: 1.6; }");
            out.println("        .pipeline-status {");
            out.println("            background: rgba(74,222,128,0.2);");
            out.println("            padding: 20px;");
            out.println("            border-radius: 10px;");
            out.println("            margin: 40px 0;");
            out.println("            border: 2px solid #4ade80;");
            out.println("        }");
            out.println("        .footer {");
            out.println("            margin-top: 60px;");
            out.println("            opacity: 0.8;");
            out.println("            font-size: 0.9em;");
            out.println("        }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='container'>");
            out.println("        <h1>🏥 Healthcare Management System</h1>");
            out.println("        <div class='subtitle'>Complete CI/CD Pipeline - Enterprise Project</div>");
            out.println("        <div class='success-badge'>✅ Application Successfully Deployed on Tomcat Server!</div>");
            out.println("        ");
            out.println("        <div class='features'>");
            out.println("            <div class='feature-card'>");
            out.println("                <div class='feature-icon'>👤</div>");
            out.println("                <div class='feature-title'>Patient Registration</div>");
            out.println("                <div class='feature-desc'>Comprehensive patient information management system</div>");
            out.println("            </div>");
            out.println("            <div class='feature-card'>");
            out.println("                <div class='feature-icon'>📅</div>");
            out.println("                <div class='feature-title'>Appointment Scheduling</div>");
            out.println("                <div class='feature-desc'>Smart doctor appointment booking and management</div>");
            out.println("            </div>");
            out.println("            <div class='feature-card'>");
            out.println("                <div class='feature-icon'>📋</div>");
            out.println("                <div class='feature-title'>Medical History</div>");
            out.println("                <div class='feature-desc'>Complete medical history tracking and retrieval</div>");
            out.println("            </div>");
            out.println("            <div class='feature-card'>");
            out.println("                <div class='feature-icon'>💊</div>");
            out.println("                <div class='feature-title'>Prescription Management</div>");
            out.println("                <div class='feature-desc'>Digital prescription recording and tracking</div>");
            out.println("            </div>");
            out.println("            <div class='feature-card'>");
            out.println("                <div class='feature-icon'>📊</div>");
            out.println("                <div class='feature-title'>Report Generation</div>");
            out.println("                <div class='feature-desc'>Automated medical report generation system</div>");
            out.println("            </div>");
            out.println("            <div class='feature-card'>");
            out.println("                <div class='feature-icon'>🔒</div>");
            out.println("                <div class='feature-title'>Security & Privacy</div>");
            out.println("                <div class='feature-desc'>HIPAA compliant data protection and encryption</div>");
            out.println("            </div>");
            out.println("        </div>");
            out.println("        ");
            out.println("        <div class='pipeline-status'>");
            out.println("            <div style='font-size: 1.5em; margin-bottom: 10px;'>Pipeline Status: ✅ All stages passed</div>");
            out.println("            <div>Built with Jenkins | Quality checked by SonarQube | Stored in Nexus</div>");
            out.println("        </div>");
            out.println("        ");
            out.println("        <div class='footer'>");
            out.println("            <p>© 2025 Healthcare Management System</p>");
            out.println("            <p>Deployed via CI/CD Pipeline: Git → Jenkins → SonarQube → Nexus → Tomcat</p>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            // Always close the writer
            out.close();
        }
    }
    
    /**
     * Returns servlet information
     */
    @Override
    public String getServletInfo() {
        return "Healthcare Management System Servlet v1.0";
    }
}
