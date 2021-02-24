package centus;

import com.centus.Image.ExpenseEvent;

import java.util.EventListener;

public interface ExpenseListener extends EventListener {
  void  zdarzenieSzastaniaPienidzmi(ExpenseEvent event);
}
