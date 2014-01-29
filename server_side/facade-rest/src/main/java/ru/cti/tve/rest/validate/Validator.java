package ru.cti.tve.rest.validate;

import java.util.Map;

public interface Validator {
	void validate(Map<String,Object> map);
}
