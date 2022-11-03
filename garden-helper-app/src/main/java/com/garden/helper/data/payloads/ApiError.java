package com.garden.helper.data.payloads;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import java.util.Collections;

public class ApiError {
	
	private HttpStatus status;
    private String message;
    private List<String> errors;

	private ApiError(Builder builder) {
		this.status = builder.status;
		this.message = builder.message;
		this.errors = builder.errors;
	}

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public static IStatusStage builder() {
		return new Builder();
	}

	public interface IStatusStage {
		public IMessageStage withStatus(HttpStatus status);
	}

	public interface IMessageStage {
		public IErrorsStage withMessage(String message);
	}

	public interface IErrorsStage {
		public IBuildStage withErrors(List<String> errors);
	}

	public interface IBuildStage {
		public ApiError build();
	}

	public static final class Builder implements IStatusStage, IMessageStage, IErrorsStage, IBuildStage {
		private HttpStatus status;
		private String message;
		private List<String> errors = Collections.emptyList();

		private Builder() {
		}

		@Override
		public IMessageStage withStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		@Override
		public IErrorsStage withMessage(String message) {
			this.message = message;
			return this;
		}

		@Override
		public IBuildStage withErrors(List<String> errors) {
			this.errors = errors;
			return this;
		}

		@Override
		public ApiError build() {
			return new ApiError(this);
		}
	}

}
