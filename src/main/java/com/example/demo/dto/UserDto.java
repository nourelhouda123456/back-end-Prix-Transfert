package com.example.demo.dto;
import com.example.demo.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private Long id;
	private String firstName;
	private String lastName;
	private String login;
	private String token;
	public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public static class UserDtoBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String login;
        private String token;

        public UserDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDtoBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserDtoBuilder token(String token) {
            this.token = token;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.setId(this.id);
            userDto.setFirstName(this.firstName);
            userDto.setLastName(this.lastName);
            userDto.setLogin(this.login);
            userDto.setToken(this.token);
            return userDto;
        }
    }

}



 

