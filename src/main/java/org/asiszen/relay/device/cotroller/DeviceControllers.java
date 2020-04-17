package org.asiszen.relay.device.cotroller;

import org.asiszen.relay.device.model.DeviceData;
import org.asiszen.relay.device.response.ApiResponse;
import org.asiszen.relay.device.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device")
public class DeviceControllers {

	@Autowired
	MessageService messageService;
	
	private static final Logger logger = LoggerFactory.getLogger(DeviceControllers.class);
	
	/* Request to get data from devices */
	@PostMapping("/queue")
	public ResponseEntity<ApiResponse> postDatatoQueue(@RequestBody DeviceData data) {

		messageService.publishMessagetoQueue(data);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),"data posted to queue",data));
	}
}
