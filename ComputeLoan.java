package computeloan;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import loan.Loan;

public class ComputeLoan extends HttpServlet {

  //process post request
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    //retrieve form parameters
    double loanAmount =
        Double.parseDouble(request.getParameter("loanAmount"));
    double annualInterestRate =
        Double.parseDouble(request.getParameter("annualInterestRate"));
    int numberOfYears =
        Integer.parseInt(request.getParameter("numberOfYears"));

    //create loan object
    Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

    double monthlyPayment = loan.getMonthlyPayment();
    double totalPayment = loan.getTotalPayment();

    //output results
    out.println("<html>");
    out.println("<head><title>Loan Payment Result</title></head>");
    out.println("<body>");
    out.println("<h3>Loan Payment Result</h3>");

    out.println("<p>Loan Amount: $" + loanAmount + "</p>");
    out.println("<p>Annual Interest Rate: " + annualInterestRate + "%</p>");
    out.println("<p>Number of Years: " + numberOfYears + "</p>");

    out.println("<p><b>Monthly Payment: $" +
                String.format("%.2f", monthlyPayment) + "</b></p>");
    out.println("<p><b>Total Payment: $" +
                String.format("%.2f", totalPayment) + "</b></p>");

    out.println("</body></html>");
    out.close();
  }
}
