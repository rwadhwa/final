package rocket.app.view;

import eNums.eAction;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;



	@FXML
	private TextField txtIncome;

	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;

	@FXML
	private TextField txtRate;

	@FXML
	private TextField txtHouseCost;

	@FXML
	private TextField txtDownPayment;

	@FXML
	private ComboBox<String> cbTerm;

	@FXML
	private TextField txtMonthlyPayment;

	@FXML
	private Button btnPayment;

	@FXML
	private Label lblCreditScore;

	@FXML
	private Label lblHouseCost;

	@FXML
	private Label lblDownPayment;

	@FXML
	private Label lblRate;

	@FXML
	private Label lblIncome;

	@FXML
	private Label lblExpenses;

	@FXML
	private Label lblTerm;

	@FXML
	private Label lblError;

	@FXML
	private Label lblMortgagePayment;

	@FXML
	private void initialize() {
		cbTerm.getItems().add("15");
		cbTerm.getItems().add("30");
		// set default cbTerm
		cbTerm.getSelectionModel().select("30");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Object message = null;
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		try {
			lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
		} catch (RateException e) {
			lq.setdRate(-1);
		}
		lq.setiTerm(Integer.parseInt(cbTerm.getSelectionModel().getSelectedItem().toString()));
		a.setLoanRequest(lq);
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		
		double rate = lRequest.getdRate();
		double pmt = Math.round(lRequest.getdPayment() * 100.00) / 100.00;
		if (rate == -1) {
			txtRate.setText("n/a");
			txtMonthlyPayment.setText("Credit score n/a");
		} else {
			txtRate.setText(Double.toString(rate));
			if ((lRequest.getiTerm() == 30 && pmt * 12 * 30 < (lRequest.getIncome() - lRequest.getExpenses() * 12) * 30)
					|| (lRequest.getiTerm() == 15
							&& pmt * 12 * 15 < (lRequest.getIncome() - lRequest.getExpenses() * 12) * 15)) {
				txtMonthlyPayment.setText(Double.toString(pmt));
			} else {
				txtMonthlyPayment.setText("The house cost is too high");
			}
		}

	}

}