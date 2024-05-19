//package kz.bitlab.bitlabfinalproject.external.service;
//
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserCreateDto;
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdateDto;
//import kz.bitlab.bitlabfinalproject.external.client.RestClient;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserRestClientService {
//
//    private final RestClient restClient;
//
//    public UserRestClientService(RestClient restClient) {
//        this.restClient = restClient;
//    }
//
//    public UserDto createUser(UserCreateDto userCreateDto) {
//        return restClient.createUser(userCreateDto);
//    }
//
//    public UserDto getUserById(Long id) {
//        return restClient.getUserById(id);
//    }
//
//    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
//        return restClient.updateUser(id, userUpdateDto);
//    }
//
//    public void deleteUser(Long id) {
//        restClient.deleteUser(id);
//    }
//}