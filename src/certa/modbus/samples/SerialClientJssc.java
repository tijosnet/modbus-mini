package certa.modbus.samples;

import certa.modbus.client.ModbusClient;
import certa.modbus.client.RtuTransportJssc;
import jssc.SerialPort;

public class SerialClientJssc {

	public static void main(String[] args) {
	
		ModbusClient mc = new ModbusClient();
		mc.setTransport(new RtuTransportJssc("COM3", 9600, 8, SerialPort.PARITY_NONE, SerialPort.STOPBITS_1, 1000, 5));
		
		mc.InitReadHoldingsRequest(1, 0, 10);

		try {
			mc.execRequest();
			if (mc.getResult() == ModbusClient.RESULT_OK)
				for (int i = 0; i < mc.getResponseCount(); i++)
					System.out.println("HR" + i + "=" + mc.getResponseRegister(mc.getResponseAddress() + i, false));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mc.close();
		}
		
	}

}
