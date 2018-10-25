account(accountId, accountNumber, accountName, accountType, currencyCode, availableBalance,
        currentBalance, availableCredit, paymentDueDate, paymentDueAmount,
         interestRate )::=
<<
{

	"account_id" : "<accountId>",
	"account_number" : "<accountNumber>",
	"account_name" : "<accountName>",
	"account_type" : "<accountType>",
	"currency_code" : "<currencyCode>",
	"available_balance" : <availableBalance>,
	"current_balance" : <currentBalance>,
	"available_credit" : <availableCredit>,
	"payment_due_date" : "<paymentDueDate>",
	"payment_due_amount" : <paymentDueAmount>,
	"interest_rate" : <interestRate>,
	"meta" : [ "meta" ]
}
>>