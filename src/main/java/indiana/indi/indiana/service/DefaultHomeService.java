package indiana.indi.indiana.service;

import indiana.indi.indiana.entity.Category;
import indiana.indi.indiana.entity.Game;
import indiana.indi.indiana.repository.CategoryRepository;
import indiana.indi.indiana.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DefaultHomeService implements HomeService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Map<Long, List<Game>> getGamesByCategory() {
        Map<Long, List<Game>> categoryGameMap = new LinkedHashMap<>();
        List<Category> categories = getAllCategories();

        for (Category category : categories) {
            List<Game> games = gameRepository.findByCategoriesContaining(category);
            categoryGameMap.put(category.getId(), games);
        }

        return categoryGameMap;
    }
}
