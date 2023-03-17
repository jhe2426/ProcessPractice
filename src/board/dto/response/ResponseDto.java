package board.dto.response;
//규칙적으로 사용자에게 반환을 해주기위해 만든 것
public  class ResponseDto<D> {
	//모든 메서드의 반환은 아래의 멤버로 반환을 할 것이다.
	//하지만 data변수의 타입이 항상 같을 수가 없기 때문에 제너릭으로 선언
	private boolean status;
	private String message;
	private D data; 

	public ResponseDto() {}

	public ResponseDto(boolean status, String message, D data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public D getData() {
		return data;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(D data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDto [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
