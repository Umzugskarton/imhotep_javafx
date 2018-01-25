package CLTrequests;

import java.io.Serializable;

public interface IRequest extends Serializable{

  String command = null;

  String getType();
}
